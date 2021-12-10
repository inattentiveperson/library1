package com.tedu.library.pojo;

public class AddOne {

        private String name;
        private String sex;
        private String birth;
        private String address;
        private String phone;
        private String passwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public AddOne(String name, String sex, String birth, String address, String phone, String passwd) {
            this.name = name;
            this.sex = sex;
            this.birth = birth;
            this.address = address;
            this.phone = phone;
            this.passwd = passwd;
        }

        public AddOne() {
        }
}

