package util;

import com.google.gson.Gson;

public class UsersManager {

    public class User {

        private String name, password, dob;
        private int age;
        private boolean connected = false;
        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        private boolean verifyPassword(String password) {
            return password.equals(this.password);
        }

        protected boolean connect(String password) {

            return verifyPassword(password);
        }

        protected void forceConnect(boolean connected) {
            this.connected = connected;
        }
        
    }
    public UsersManager() {
        User user = new User("t", "t");

        Gson gson = new Gson();
        IO.println(gson.toJson(user));
    }
}
