package com.example.dat153.Utils;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "picture_table")
    public class Picture {
        @PrimaryKey(autoGenerate = true)
        private int id;
        private String title;
        private byte[] image;


        public Picture(String title, byte[] image) {
            this.title = title;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public int getId() {
            return id;
        }

        public byte[] getImage() {
            return image;
        }

        public void setId(int id) {
            this.id = id;
        }
    }



