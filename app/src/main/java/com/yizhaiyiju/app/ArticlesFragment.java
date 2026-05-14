package com.yizhaiyiju.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i1;
import com.yizhaiyiju.app.ApiHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ArticlesFragment extends Fragment {
    private ArticleAdapter adapter;
    private List<ArticleItem> articles = new ArrayList();

    public class ArticleAdapter extends androidx.recyclerview.widget.g0<ArticleAdapter.VH> {
        List<ArticleItem> data;

        public class VH extends i1 {
            TextView tvDate;
            TextView tvSummary;
            TextView tvTitle;

            public VH(View view) {
                super(view);
                this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
                this.tvSummary = (TextView) view.findViewById(R.id.tv_summary);
                this.tvDate = (TextView) view.findViewById(R.id.tv_date);
            }
        }

        public ArticleAdapter(List<ArticleItem> list) {
            this.data = list;
        }

        @Override
        public int getItemCount() {
            return this.data.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(ArticleItem articleItem, View view) {
            Intent intent = new Intent(ArticlesFragment.this.getActivity(), (Class<?>) ArticleDetailActivity.class);
            intent.putExtra("article_title", articleItem.title);
            intent.putExtra("article_url", articleItem.url);
            ArticlesFragment.this.startActivity(intent);
        }

        @Override
        public void onBindViewHolder(VH vh, int i4) {
            final ArticleItem articleItem = this.data.get(i4);
            vh.tvTitle.setText(articleItem.title);
            vh.tvSummary.setText(articleItem.summary);
            vh.tvDate.setText(articleItem.date);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ArticlesFragment.this.getActivity(), (Class<?>) ArticleDetailActivity.class);
                    intent.putExtra("article_title", articleItem.title);
                    intent.putExtra("article_url", articleItem.url);
                    ArticlesFragment.this.startActivity(intent);
                }
            });
        }

        @Override
        public VH onCreateViewHolder(ViewGroup viewGroup, int i4) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false));
        }
    }

    public class ArticleItem {
        String date;
        String summary;
        String title;
        String url;

        public ArticleItem(String str, String str2, String str3, String str4) {
            this.title = str;
            this.summary = str2;
            this.date = str3;
            this.url = str4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).switchToTab(R.id.nav_home);
        }
    }

    private void loadArticles() {
        ApiHelper.getArticles(new ApiHelper.Callback<List<ApiHelper.Article>>() {
            @Override
            public void onError(String str) {
            }

            @Override
            public void onSuccess(List<ApiHelper.Article> list) {
                ArticlesFragment.this.articles.clear();
                for (ApiHelper.Article article : list) {
                    ArticlesFragment.this.articles.add(ArticlesFragment.this.new ArticleItem(article.title, article.summary, article.date, article.url));
                }
                ArticlesFragment.this.adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public u0.b getDefaultViewModelCreationExtras() {
        return u0.a.f4680b;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_articles, viewGroup, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.top_bar), (v, insets) -> {
            androidx.core.graphics.Insets bars = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.systemBars());
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() + bars.top, v.getPaddingRight(), v.getPaddingBottom());
            return insets;
        });
        androidx.core.view.ViewCompat.requestApplyInsets(view.findViewById(R.id.top_bar));
        view.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).switchToTab(R.id.nav_home);
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArticleAdapter articleAdapter = new ArticleAdapter(this.articles);
        this.adapter = articleAdapter;
        recyclerView.setAdapter(articleAdapter);
        loadArticles();
    }
}
