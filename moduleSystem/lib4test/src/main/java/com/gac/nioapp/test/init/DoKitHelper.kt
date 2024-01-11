package com.gac.nioapp.test.init

import android.content.Intent
import com.didichuxing.doraemonkit.DoKit
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.didichuxing.doraemonkit.kit.network.okhttp.interceptor.DokitExtInterceptor
import com.gac.nioapp.test.helper.ApplicationHelper
import com.gac.nioapp.test.kit.IDeveloperKit
import com.gac.nioapp.test.kit.PublicDeveloperKit
import com.gac.nioapp.test.router.ARouterManager
import com.gac.nioapp.test.router.RouterConstant
import com.gac.nioapp.test.utils.GwServiceLoader
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @className DoKitHelper
 * @description TODO
 * @author heyufei
 * @since 2021/12/2 3:48 下午
 * @version 1.0
 */
class DoKitHelper {
    fun init() {
        val configKits = LinkedHashMap<String, List<AbstractKit>>()

        val user_kits: Iterator<IDeveloperKit>? = GwServiceLoader.load(
                IDeveloperKit::class.java
        )
        val public_kits: Iterator<PublicDeveloperKit>? = GwServiceLoader.load(
                PublicDeveloperKit::class.java
        )
        val userKits = ArrayList<AbstractKit>()

        while (true == user_kits?.hasNext()) {
            userKits.add(user_kits.next().getAbstractKit())
        }
        val publicKits = ArrayList<AbstractKit>()
        while (true == public_kits?.hasNext()) {
            publicKits.add(public_kits.next().getPublicDeveloperKit())
        }

        configKits["开发测试代码"] = userKits
        configKits["公共测试代码"] = publicKits
        DoKit.Builder(ApplicationHelper.getApplication())
                .productId("xxxxxx")
                .disableUpload()
                .fileManagerHttpPort(9001)
                .mcWSPort(5555)
                .alwaysShowMainIcon(true)
                .webDoorCallback { context, url ->
                    //h5跳转
                    val intent = Intent()
                    intent.putExtra(RouterConstant.URL, url)
                    intent.putExtra(RouterConstant.TITLE, "调试页面")
                    ARouterManager.getInstance()
                            .startARActivityPble(RouterConstant.PATH_TO_WEBVIEW_ACTIVITY, intent)

                }
                .netExtInterceptor(object : DokitExtInterceptor.DokitExtInterceptorProxy {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        return chain.proceed(chain.request())
                    }

                })
                .customKits(configKits)
                .build()
    }
}