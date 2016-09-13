package dev.nightq.wts.ui.article.list.fragmet;

import dagger.Component;
import dev.nightq.wts.app.component.UserComponent;
import dev.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/8/18.
 */
@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {ArticlesListModule.class})
public interface ArticlesListComponent {
    ArticlesListFragment inject(ArticlesListFragment fragment);
}