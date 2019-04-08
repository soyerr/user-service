package org.demo.application.userservice.util;


import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.controller.model.RestUser;

public class RestModelBuilder {

    public static RestAddress buildSpecificRestAddress(int number){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(number);
        restAddress.setHousenumber(number);
        restAddress.setStreetname("street"+number);

        return restAddress;
    }


    public static RestUser buildSpecificRestUser(int number){
        RestUser restUser = new RestUser();
        restUser.setAge(number);
        restUser.setName("name"+number);
        restUser.setSurname("surname"+number);

        return restUser;
    }

    public static RestUser buildRestUser(){
        RestUser restUser = new RestUser();
        restUser.setAge(0);
        restUser.setName("John");
        restUser.setSurname("Smith");

        return restUser;
    }

    public static RestAddress buildRestAddress(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(5);
        restAddress.setHousenumber(10);
        restAddress.setStreetname("StreetName");

        return restAddress;
    }

    public static RestUser buildRestUserWithoutName(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestUser buildRestUserWithEmptyName(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setName("");
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestUser buildRestUserWithBlankName(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setName(" ");
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestUser buildRestUserWithoutSurname(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setName("John");
        return restUser;
    }

    public static RestUser buildRestUserWithEmptySurname(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setName("John");
        restUser.setSurname("");
        return restUser;
    }

    public static RestUser buildRestUserWithBlankSurname(){
        RestUser restUser = new RestUser();
        restUser.setAge(1);
        restUser.setName("John");
        restUser.setSurname(" ");
        return restUser;
    }

    public static RestUser buildRestUserWithAgeBelowLowBoundary(){
        RestUser restUser = new RestUser();
        restUser.setAge(0);
        restUser.setName("John");
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestUser buildRestUserWithAgeAboveTopBoundary(){
        RestUser restUser = new RestUser();
        restUser.setAge(151);
        restUser.setName("John");
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestUser buildRestUserWithoutAge(){
        RestUser restUser = new RestUser();
        restUser.setName("John");
        restUser.setSurname("Smith");
        return restUser;
    }

    public static RestAddress buildRestAddressWithoutStreet(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(10);
        restAddress.setHousenumber(10);
        return restAddress;
    }

    public static RestAddress buildRestAddressWithEmptyStreeet(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(10);
        restAddress.setHousenumber(10);
        restAddress.setStreetname("");
        return restAddress;
    }

    public static RestAddress buildRestAddressWithBlankStreeet(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(10);
        restAddress.setHousenumber(10);
        restAddress.setStreetname(" ");
        return restAddress;
    }

    public static RestAddress buildRestAddressWithoutFlat(){
        RestAddress restAddress = new RestAddress();
        restAddress.setHousenumber(10);
        restAddress.setStreetname("Street");
        return restAddress;
    }

    public static RestAddress buildRestAddressWithAFlatBelowLowBoundary(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(0);
        restAddress.setHousenumber(10);
        restAddress.setStreetname("Street");
        return restAddress;
    }

    public static RestAddress buildRestAddressWithoutHouse(){
        RestAddress restAddress = new RestAddress();
        restAddress.setStreetname("Street");
        restAddress.setFlatnumber(10);
        return restAddress;
    }

    public static RestAddress buildRestAddressWithAHouseBelowLowBoundary(){
        RestAddress restAddress = new RestAddress();
        restAddress.setFlatnumber(10);
        restAddress.setHousenumber(0);
        restAddress.setStreetname("Street");
        return restAddress;
    }
}
