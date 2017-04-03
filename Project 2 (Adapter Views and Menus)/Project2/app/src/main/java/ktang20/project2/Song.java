package ktang20.project2;

public class Song {

    String name = null;
    int code = -1;
    boolean selected = false;

    public Song(String name, int code, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    //Return the name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    //Returns whether the item is selected or not
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
