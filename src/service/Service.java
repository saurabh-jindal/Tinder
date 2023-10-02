package service;

import enums.Gender;
import model.Location;
import model.User;
import utils.Distance;

import java.util.*;

public class Service {
    private static final Service service = new Service();
    private Service(){};
    public static Service getInstance(){
        return service;
    }
    HashSet<User> allUsers = new HashSet<>();
    HashMap<User, HashSet<User>> matchList = new HashMap<>();
    Distance distance = Distance.getInstance();
    public void createUser(String name, Integer x, Integer y, Integer age, String gender){
        Location location = new Location(x, y);
        Gender genderEnum = Gender.valueOf(gender);
        User user = new User(name, genderEnum, age, location);
        allUsers.add(user);
        System.out.println("User Created");
    }

    public void deleteAccount(String name){
        for(User user: allUsers){
            if(user.getName().equals(name)){
                allUsers.remove(user);
                System.out.println("User deleted");
                return;
            }
        }
        System.out.println("No User Found");
    }

    public void like(String a, String b){
        User usera = null;
        User userb = null;
        for(User user: allUsers){
            if(user.getName().equals(a)){
                usera = user;
            }
            if(user.getName().equals(b)){
                userb = user;
            }
        }
        if(Objects.nonNull(usera) && Objects.nonNull(userb)){
            usera.addLikes(userb);
            if(userb.getLikes().contains(usera)){
                if(!matchList.containsKey(usera)){
                    matchList.put(usera, new HashSet<>());
                    matchList.put(userb, new HashSet<>());
                    matchList.get(usera).add(userb);
                    matchList.get(userb).add(usera);
                }else{
                    matchList.get(usera).add(userb);
                    matchList.get(userb).add(usera);
                }
            }
            System.out.println("Added to Likes List");
            return;
        }
        System.out.println("User not found");
    }

    public void ignore(String a, String b){
        User usera = null;
        User userb = null;
        for(User user: allUsers){
            if(user.getName().equals(a)){
                usera = user;
            }
            if(user.getName().equals(b)){
                userb = user;
            }
        }
        if(Objects.nonNull(usera) && Objects.nonNull(userb)){
            if(usera.getLikes().contains(userb)){
                usera.getLikes().remove(userb);
                System.out.println("Removed from Likes List");
                return;
            }else{
                System.out.println("Not found in  Likes List");
                return;
            }
        }
        System.out.println("User not found");
    }

    public void showMatches(String username){
        User user = null;
        for(User tempuser: allUsers){
            if(tempuser.getName().equals(username)){
                user = tempuser;
            }
        }
        if(Objects.nonNull(user) && matchList.containsKey(user)){
            System.out.print(user + ": ");
            for(User user_a: matchList.get(user)){
                System.out.println(user_a);
            }
        }
    }

    public void showAllMatches(){
        for(User user: matchList.keySet()){
            System.out.println(user + ": ");
            for(User user1: matchList.get(user)){
                System.out.println(user1);
            }
        }
    }

    public void potentialMatches(String username){
        List<User> probableMatches = new ArrayList<>();
        Gender gender = null;
        Integer age = 0;
        for(User user: allUsers){
            if(!user.getName().equals(username)){
                probableMatches.add(user);
            }else{
                gender = user.getGender();
                age = user.getAge();
            }
        }
        Gender finalGender = gender;
        Integer finalAge = age;
        probableMatches.sort((o1, o2) -> {
            Location location = new Location(0, 0);
            boolean isMatch1 = o1.getGender().equals(finalGender);
            boolean isMatch2 = o2.getGender().equals(finalGender);
            if (isMatch1 && !isMatch2) {
                return 1;
            } else if (!isMatch1 && isMatch2) {
                return -1;
            } else {
                int distance1 = distance.euclideanDistance(o1.getLocation(), location);
                int distance2 = distance.euclideanDistance(o2.getLocation(), location);

                if(distance1 != distance2){
                    return Integer.compare(distance1, distance2);
                } else {
                    int ageDifference1 = Math.abs(o1.getAge() - finalAge);
                    int ageDifference2 = Math.abs(o2.getAge() - finalAge);

                    return Integer.compare(ageDifference1, ageDifference2);
                }
            }
        });

        for(User user: probableMatches){
            System.out.println(user);
        }
    }
}
