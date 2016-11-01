package com.adgvcxz.adgble.binding;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adgvcxz.adgble.util.Util;
import com.adgvcxz.adgble.view.TextViewFixTouchConsume;

/**
 * zhaowei
 * Created by zhaowei on 2016/10/30.
 */

public class TextViewBindingConfig {

    @BindingAdapter({"html"})
    public static void setHtml(TextView textView, String html) {
        if (!TextUtils.isEmpty(html)) {
            Spanned spanned = Html.fromHtml(html);
            URLSpan[] spans = spanned.getSpans(0, spanned.length(), URLSpan.class);
            if (spanned instanceof SpannableStringBuilder) {
                SpannableStringBuilder builder = (SpannableStringBuilder) spanned;
                for (URLSpan span : spans) {
                    int start = spanned.getSpanStart(span);
                    int end = spanned.getSpanEnd(span);
                    builder.removeSpan(span);
                    builder.setSpan(new DribbbleUrlSpan(span.getURL()), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                textView.setText(trimHtml(builder));
            } else {
                textView.setText(trimHtml(spanned));
            }
            textView.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
        }
    }

    private static CharSequence trimHtml(CharSequence text) {

        while (Character.isWhitespace(text.charAt(text.length() - 1))) {
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }


    private static class DribbbleUrlSpan extends URLSpan {

        public static final Parcelable.Creator<DribbbleUrlSpan> CREATOR = new Parcelable.Creator<DribbbleUrlSpan>() {
            public DribbbleUrlSpan createFromParcel(Parcel source) {
                return new DribbbleUrlSpan(source);
            }

            public DribbbleUrlSpan[] newArray(int size) {
                return new DribbbleUrlSpan[size];
            }
        };

        DribbbleUrlSpan(String url) {
            super(url);
        }

        DribbbleUrlSpan(Parcel src) {
            super(src);
        }

        @Override
        public void onClick(View widget) {
            if (getURL().contains(Util.APP)) {
                //TODO click @ Dribble User ...
                Toast.makeText(widget.getContext(), "Click Dribbble User", Toast.LENGTH_SHORT).show();
            } else {
                super.onClick(widget);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

}
