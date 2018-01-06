package gsolutions.alarm;


/**
 * Created by גלעד on 1/5/2018.
 */

public class user {
    public String name;
    public String group;
    user() {
        // empty constructor required by jackson
    }
    user(String name, String group) {
        this.name = name;
        this.group = group;
    }
    public void setUserGroup(String group)
    {
        this.group = group;
    }
}
