package com.hjf.wanandroid.vh.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.been.ArticleInfo;
import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.vh.BaseViewHolder;

import butterknife.BindView;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class ArticleVH extends BaseViewHolder<WanAndroidInfo> {

    @BindView(R.id.article_title)
    TextView article_title;
    @BindView(R.id.article_author)
    TextView article_author;
    @BindView(R.id.article_nice_date)
    TextView article_nice_date;

    public ArticleVH(ViewGroup parent, View.OnClickListener listener) {
        super(R.layout.vh_home_article, parent, listener);
    }

    @Override
    public void bind(WanAndroidInfo data) {
        Object object = data.getObject();
        if (object instanceof ArticleInfo.DataBean.DatasBean) {
            ArticleInfo.DataBean.DatasBean datasBean = (ArticleInfo.DataBean.DatasBean) object;
            article_title.setText(datasBean.getTitle());
            article_author.setText(datasBean.getAuthor());
            article_nice_date.setText(datasBean.getNiceDate());
        }
    }
}
