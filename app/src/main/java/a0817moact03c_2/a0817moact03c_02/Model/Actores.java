package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by ma on 01/12/17.
 */

public class Actores {
    private String id;
    private String name;
    private String profile_path;
    private String character;

    private String birthday;
    private String biography;
    private String also_known_as;

    public Actores(String id, String name, String profile_path, String character, String birthday, String biography, String also_known_as) {
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.character = character;
        this.birthday = birthday;
        this.biography = biography;
        this.also_known_as = also_known_as;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return "https://image.tmdb.org/t/p/w500"+profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(String also_known_as) {
        this.also_known_as = also_known_as;
    }
}
