package com.company;
import com.company.socialmedia.ActionHandler;
import com.company.socialmedia.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();
        ActionHandler actionHandler = new ActionHandler(userList);
        String command = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Type your username followed by an action!");
        while (true) {
            try {
                command = s.nextLine();
                actionHandler.processAction(command);
            }
            catch (Exception e){
                throw new IllegalArgumentException(e);
            }
        }
    }
}
