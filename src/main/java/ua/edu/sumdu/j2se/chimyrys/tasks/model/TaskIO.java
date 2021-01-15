package ua.edu.sumdu.j2se.chimyrys.tasks.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(out)) {
            dataOutputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                dataOutputStream.writeInt(task.getTitle().length());
                dataOutputStream.writeChars(task.getTitle());
                dataOutputStream.writeBoolean(task.isActive());
                dataOutputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    dataOutputStream.writeLong(task.getStartTime().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
                    dataOutputStream.writeLong(task.getEndTime().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
                } else {
                    dataOutputStream.writeLong(task.getTime().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
                }
            }
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int titleLength = dataInputStream.readInt();
                char[] chars = new char[titleLength];
                for (int j = 0; j < titleLength; j++) {
                    chars[j] = dataInputStream.readChar();
                }
                String title = String.valueOf(chars);
                boolean isActive = dataInputStream.readBoolean();
                int interval = dataInputStream.readInt();
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()), ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()), ZoneId.systemDefault());
                    Task task = new Task(title, start, end, interval);
                    task.setActive(isActive);
                    tasks.add(task);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()), ZoneId.systemDefault());
                    Task task = new Task(title, time);
                    task.setActive(isActive);
                    tasks.add(task);
                }
            }
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            write(tasks, outputStream);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            read(tasks, inputStream);
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                    .create();
            Task[] arrayTasks = new Task[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                arrayTasks[i] = tasks.getTask(i);
            }
            gson.toJson(arrayTasks, out);
        } finally {
            out.close();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
                    .create();
            Task[] arrayTasks = gson.fromJson(in, Task[].class);
            for (Task task : arrayTasks) {
                tasks.add(task);
            }
        } finally {
            in.close();
        }
    }
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            write(tasks, writer);
        }
    }
    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            read(tasks, reader);
        }
    }
}
