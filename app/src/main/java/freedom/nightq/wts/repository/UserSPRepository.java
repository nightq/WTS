package freedom.nightq.wts.repository;

import android.content.SharedPreferences;

import javax.inject.Inject;

import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.scope.UserScope;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.tools.Constants;

/**
 * Created by Nightq on 16/9/7.
 */

@UserScope
public class UserSPRepository {

    /**
     * user 本地的 存储
     */
    SharedPreferences mUserSP;

    @Inject
    public UserSPRepository(
            WTSApplication application,
            User user) {
        mUserSP = application.getSharedPreferences(
                Constants.Config.USER_SP + user.getId(),
                0);
    }

    public SharedPreferences getUserSP() {
        return mUserSP;
    }
}
