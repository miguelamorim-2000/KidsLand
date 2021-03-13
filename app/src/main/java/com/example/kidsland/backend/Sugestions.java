package com.example.kidsland.backend;

public class Sugestions {
    public class Sugestion {

        private int id_sugestion;
        private int id_tutor;
        private String name;
        private int total_point;
        private String birth_date;
        private String photo;

        public Sugestion() {
        }

        public Sugestion(int id_sugestion, int id_tutor, String name, int total_point, String birth_date, String photo) {
            this.id_sugestion = id_sugestion;
            this.id_tutor = id_tutor;
            this.name = name;
            this.total_point = total_point;
            this.birth_date = birth_date;
            this.photo = photo;
        }

        public int getId_sugestion() {
            return id_sugestion;
        }

        public void setId_sugestion(int id_sugestion) {
            this.id_sugestion = id_sugestion;
        }

        public int getId_tutor() {
            return id_tutor;
        }

        public void setId_tutor(int id_tutor) {
            this.id_tutor = id_tutor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal_point() {
            return total_point;
        }

        public void setTotal_point(int total_point) {
            this.total_point = total_point;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        @Override
        public String toString() {
            return "Sugestion{" + "id_sugestion=" + id_sugestion + ", id_tutor=" + id_tutor + ", name=" + name + ", total_point=" + total_point + ", birth_date=" + birth_date + ", photo=" + photo + '}';
        }



    }

}
