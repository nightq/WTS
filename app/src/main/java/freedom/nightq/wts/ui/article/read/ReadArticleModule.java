package freedom.nightq.wts.ui.article.read;


import dagger.Module;
import freedom.nightq.wts.app.baseView.DaggerBaseModule;

/**
 * Created by Nightq on 16/8/22.
 */

@Module
public class ReadArticleModule extends DaggerBaseModule<ReadArticleContract.View> {

    public ReadArticleModule(ReadArticleContract.View view) {
        super(view);
    }

}
