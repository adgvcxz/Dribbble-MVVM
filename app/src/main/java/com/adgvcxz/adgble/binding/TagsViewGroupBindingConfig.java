package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.view.View;

import com.adgvcxz.adgble.view.TagsViewGroup;

import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/3.
 */

public class TagsViewGroupBindingConfig {

    @BindingAdapter({"tags"})
    public static void setTags(TagsViewGroup tagsViewGroup, List<String> tags) {
        tagsViewGroup.setTags(tags);
    }

}
