package com.adgvcxz.adgble.util;

import android.os.Parcel;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/29.
 */

public class GoURLSpan extends ClickableSpan {


    private static final String SLASH = "/";
    private final String mURL;

    public GoURLSpan(String url) {
        mURL = url;
    }

    public GoURLSpan(Parcel src) {
        mURL = src.readString();
    }

    public static Spanned hackURLSpan(Spanned spanText) {
        if (spanText instanceof SpannableStringBuilder) {
            SpannableStringBuilder text = (SpannableStringBuilder) spanText;
            hackURLSpanHasResult(text);
            return text;
        }
        return spanText;
    }

    /**
     * @param spanText
     * @return true if have url
     */
    public static boolean hackURLSpanHasResult(SpannableStringBuilder spanText) {
        boolean result = false;
        URLSpan[] spans = spanText.getSpans(0, spanText.length(), URLSpan.class);
        // TODO URLSpan need change to ClickableSpan (GoURLSpan) , otherwise URLSpan can not click, not display underline.WHY?
        for (URLSpan span : spans) {
            int start = spanText.getSpanStart(span);
            int end = spanText.getSpanEnd(span);
            String url = span.getURL();
            if (url != null) {
                result = true;
                spanText.removeSpan(span);
                ClickableSpan span1 = new GoURLSpan(span.getURL());
                spanText.setSpan(span1, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return result;
    }

    public String getURL() {
        return mURL;
    }

    @Override
    public void onClick(View widget) {
        // TODO handler inapp
        String url = getURL();

        //        ArrayList<String> imgs = (ArrayList<String>) widget.getTag(R.id.poste_image_data);
        //        if (imgs != null && imgs.contains(url)) {
        //            Context a = widget.getContext();
        //            final int index = imgs.indexOf(url);
        //            String[] img = new String[imgs.size()];
        //            img = imgs.toArray(img);
        //            ActivityUtils.openPhotosActivity(a, index, img);
        //            return;
        //        }

//        if (url.startsWith(SLASH)) {
//            url = SITE_URL + url;
//        }
//        if (url.startsWith(SITE_URL) && !Utils.isSpecialUrl(url)) {
//            handleInAppClicked(widget);
//        } else {
//            openUrl(widget);
//        }
    }
}

