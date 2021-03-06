package freedom.nightq.wts.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avos.sns.SNS;
import com.avos.sns.SNSType;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import freedom.nightq.wts.R;
import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.baseView.activity.MVPActivityBase;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.tools.Constants;
import freedom.nightq.wts.tools.ResourceHelper;
import freedom.nightq.wts.tools.ToastHelper;

/**
 * A login screen that offers login via email/password.
 * session 状态变化会有全局的 event 发出
 */
public class LoginActivity
        extends MVPActivityBase<LoginPresenter>
        implements LoginContract.View {

    /**
     * QQ
     */
    private static final String QQ_APP_ID = "1105611977";
    private static final String QQ_APP_SEC = "HDUG0fIbJThScfAE";
    private static final String QQ_APP_URL = "https://leancloud.cn/1.1/sns/goto/2mt4iu7n6pwsv370";

    /**
     * WEIBO
     */
    private static final String WEIBO_APP_ID = "728184422";
    private static final String WEIBO_APP_SEC = "150e64dd066d18d63ee0304ac242308d";
    private static final String WEIBO_APP_URL = "https://leancloud.cn/1.1/sns/goto/6l6n90b9f08sya8d";

    @Bind(R.id.btnLoginQQ)
    Button btnLoginQQ;
    @Bind(R.id.btnLoginWeibo)
    Button btnLoginWeibo;

    @Inject
    public User mUser;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {

    }

    @Override
    public boolean reinjectWhenSessionChange() {
        return true;
    }

    @Override
    public int onCreateBase() {
        return R.layout.activity_login;
    }

    @Override
    public void initActivityBaseView() {

    }

    @Override
    public void loadDataOnCreate() {
    }

    @Override
    public void setupComponent() {
        DaggerLoginComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    protected void onSessionChange() {
        super.onSessionChange();
        if (mUser.checkValidSession(false)) {
            // session 状态变了,并且有登录态了。那就结束返回
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void loginFailed(Exception e) {
        ToastHelper.show(
                ResourceHelper.getString(
                        R.string.login_toast_error, ((e == null) ? "" : e.getMessage())));
    }

    SNSType lastLoginType;

    @OnClick({
            R.id.btnLoginQQ,
            R.id.btnLoginWeibo,
            R.id.btnLoginLean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginQQ:
                lastLoginType = SNSType.AVOSCloudSNSQQ;
                mPresenter.login(
                        this,
                        lastLoginType,
                        QQ_APP_ID,
                        QQ_APP_SEC,
                        QQ_APP_URL);
                break;
            case R.id.btnLoginWeibo:
                lastLoginType = SNSType.AVOSCloudSNSSinaWeibo;
                mPresenter.login(
                        this,
                        lastLoginType,
                        WEIBO_APP_ID,
                        WEIBO_APP_SEC,
                        WEIBO_APP_URL);
                break;
            case R.id.btnLoginLean:
//                lastLoginType = SNSType.AVOSCloudSNS;
//                mPresenter.login(
//                        this,
//                        lastLoginType,
//                        "1105611977",
//                        "HDUG0fIbJThScfAE",
//                        "https://leancloud.cn/1.1/sns/goto/2mt4iu7n6pwsv370");
                break;
        }
    }

    // 当登录完成后，请调用 SNS.onActivityResult(requestCode, resultCode, data, type);
    // 这样你的回调用将会被调用到
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SNS.onActivityResult(requestCode, resultCode, data, lastLoginType);
    }

    /**
     * login
     */
    public static void startLoginActivity (Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, Constants.ActivityRequest.AR_LOGIN);
    }

}

