package com.github.m5rian.shilu.client.utilities;

import com.google.gson.Gson;

import java.io.*;

/**
 * @author Marian
 * The file manager for the client.
 */
public class Storage {
    private static Gson gson = new Gson();

    /**
     * The root directory where all data for the client is stored.
     */
    private static File ROOT_DIR = new File("Shilu Client");
    /**
     * the directory where the mod settings are sitting.
     */
    private static File MODS_DIR = new File(ROOT_DIR, "mods");

    /**
     * Creates all needed directories if they don't exist yet.
     */
    public static void init() {
        if (!ROOT_DIR.exists()) ROOT_DIR.mkdirs();
        if (!MODS_DIR.exists()) MODS_DIR.mkdirs();
    }

    public static Gson getGson() {
        return gson;
    }

    /**
     * @return Returns the {@link Storage#MODS_DIR}.
     */
    public static File getModsDir() {
        return MODS_DIR;
    }

    /**
     * Writes an object to a .json file.
     *
     * @param file   The file which gets written.
     * @param object The Object to parse to json.
     * @return Returns true if the file got written successfully. Else returns false.
     */
    public static boolean writeJsonToFile(File file, Object object) {
        try {
            if (!file.exists()) file.createNewFile();
            final FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(gson.toJson(object).getBytes());
            outputStream.flush();
            outputStream.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads a .json file and parse it to the provided {@param clazz}.
     *
     * @param file  The file to read.
     * @param clazz The class to parse it to.
     * @param <T>   T Class type thingy? not sure how this is called...
     * @return Returns {@param T} from the json file.
     */
    public static <T> T readFromJson(File file, Class<T> clazz) {
        try {
            final FileInputStream inputStream = new FileInputStream(file);
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            final StringBuilder string = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string.append(line);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

            return gson.fromJson(string.toString(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if a specific file exists.
     *
     * @param file The file to check if it exists.
     * @return Returns true if the {@param file} exists. Else returns false.
     */
    public static boolean fileExists(File file) {
        return file.exists();
    }

}