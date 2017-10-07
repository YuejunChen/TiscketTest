package test.study.select_city.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author zaaach on 2015/1/26.
 */
public class WrapHeightGridView extends GridView {
    public WrapHeightGridView(Context context) {
        this(context, null);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //如果把GridView放到一个垂直方向滚动的布局中，设置其高度属性为 wrap_content ,
    // 则该GridView的高度只有一行内容，其他内容通过滚动来显示。
    // 如果你想让该GridView的高度为所有行内容所占用的实际高度，则可以通过覆写GridView的 onMeasure 函数来修改布局参数
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
