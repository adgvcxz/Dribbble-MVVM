package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;

import com.adgvcxz.adgble.view.TagsViewGroup;

import java.util.List;

/**
 * zhaowei
 * Created by zhaowei on 2016/11/3.
 */

public class TagsViewGroupBindingConfig {

    @BindingAdapter({"tags", "tagClickListener"})
    public static void setTags(TagsViewGroup tagsViewGroup, List<String> tags, TagsViewGroup.OnTagClickListener listener) {
        tagsViewGroup.setTags(tags, listener);
    }

}
