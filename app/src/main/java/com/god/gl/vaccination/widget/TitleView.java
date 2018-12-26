package com.god.gl.vaccination.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.god.gl.vaccination.R;


/**
 * @author gl
 * @date 2018/5/7
 * @desc 头部标题
 */
public class TitleView extends FrameLayout {
    private Context mContext;
    private ImageView mBack;
    private TextView mTitle;
    private ImageView mRightIv;
    private TextView mRightTitle;
    private RightTitleListener mRightTitleListener;
    private RightIvListener mRightIvListener;
    private BackListener mBackListener;
    private RelativeLayout mRlContent;

    public TitleView(@NonNull Context context) {
        this(context, null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        //加载布局
        LayoutInflater.from(mContext).inflate(R.layout.view_title, this);
        mBack = findViewById(R.id.back);
        mTitle = findViewById(R.id.title);
        mRightTitle = findViewById(R.id.rightTitle);
        mRightIv = findViewById(R.id.rightIv);
        mRlContent = findViewById(R.id.rl_content);
        //获取自定义引用
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.title);
        String titleName = a.getString(R.styleable.title_title_name);
        String rightTitleName = a.getString(R.styleable.title_right_title_name);
        Drawable rightDrawable = a.getDrawable(R.styleable.title_iv_image_right);
        boolean rightTitleShow = a.getBoolean(R.styleable.title_right_title_show, false);
        boolean rightIvshow = a.getBoolean(R.styleable.title_iv_right_show, false);
        int color = a.getColor(R.styleable.title_bg_color, context.getResources().getColor(R.color.blue));
        int textColor = a.getColor(R.styleable.title_text_color, context.getResources().getColor(R.color.white));
        Drawable leftDrawable = a.getDrawable(R.styleable.title_iv_image_left);

        a.recycle();
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        if (null != mRlContent) {
            mRlContent.setBackgroundColor(color);
        }

        if (null != mTitle) {
            mTitle.setTextColor(textColor);
        }
        if (null != mRightTitle) {
            mRightTitle.setTextColor(textColor);
        }
        if (rightTitleShow) {
            mRightTitle.setVisibility(View.VISIBLE);
        } else {
            mRightTitle.setVisibility(View.GONE);
        }

        if (rightIvshow) {
            mRightIv.setVisibility(View.VISIBLE);
        } else {
            mRightIv.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(titleName)) {
            mTitle.setText(titleName);
        }

        if (!TextUtils.isEmpty(rightTitleName)) {
            mRightTitle.setText(rightTitleName);
        }

        if (null != rightDrawable) {
            mRightIv.setImageDrawable(rightDrawable);
        }

        if (null != leftDrawable) {
            mBack.setImageDrawable(leftDrawable);
        }

        //设置监听
        mRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mRightTitleListener) {
                    mRightTitleListener.rightTitleClick();
                }
            }
        });
        mRightIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mRightIvListener) {
                    mRightIvListener.rightIvClick();
                }
            }
        });

        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mBackListener) {
                    mBackListener.backClick();
                }
            }
        });

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (null != mTitle) {
            mTitle.setText(title);
        }

    }

    /**
     * 设置右标题
     *
     * @param rightTitle
     */
    public void setRightTitle(String rightTitle) {
        if (null != mRightTitle) {
            mRightTitle.setText(rightTitle);
        }

    }

    /**
     * 设置右图标
     *
     * @param drawable
     */
    public void setRightIv(Drawable drawable) {
        if (null != mRightIv) {
            mRightIv.setImageDrawable(drawable);
        }


    }

    /**
     * 设置返回键
     *
     * @param drawable
     */
    public void setLeftIv(Drawable drawable) {
        if (null != mBack) {
            mBack.setImageDrawable(drawable);
        }
    }

    public interface RightTitleListener {
        void rightTitleClick();
    }

    public interface RightIvListener {
        void rightIvClick();
    }

    public interface BackListener {
        void backClick();
    }

    public void setRightTitleListener(RightTitleListener rightTitleListener) {
        mRightTitleListener = rightTitleListener;
    }

    public void setRightIvListener(RightIvListener rightIvListener) {
        mRightIvListener = rightIvListener;
    }

    public void setBackListener(BackListener backListener) {
        mBackListener = backListener;
    }
}
