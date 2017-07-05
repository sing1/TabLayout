package sing.tablayout;

import java.io.Serializable;

public class ViewPagerBean implements Serializable {
    public MyFragment fragment;
    public String title;

    public ViewPagerBean(MyFragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }
}