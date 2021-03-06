package com.ooftf.vertical

import android.support.v4.view.ScrollingView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ScrollView

/**
 * Created by 99474 on 2017/12/25 0025.
 */

class ScrollEdgeEngine(var scrollView: View) : EdgeWrapper {
    override fun isTop(): Boolean {
        return if (scrollView is ScrollingView) {
            var scrollingView  = scrollView as ScrollingView
            scrollingView.computeVerticalScrollOffset() == 0;
        } else {
            scrollView.scrollY == 0
        }
    }

    override fun isBottom(): Boolean {
        return if (scrollView is ScrollingView) {
            val scrollingView  = scrollView as ScrollingView
            scrollingView.computeVerticalScrollExtent()+scrollingView.computeVerticalScrollOffset() >= scrollingView.computeVerticalScrollRange()
        }else if (scrollView is WebView){
            val webView =  scrollView as WebView
            if(BuildConfig.DEBUG){
                Log.e(((webView.height+webView.scrollY)*1.001).toString(),(webView.contentHeight*webView.scale).toString())
            }
            (webView.height+webView.scrollY)*1.001 >=webView.contentHeight*webView.scale//1.001作用 :有些网站会有误差，具体原因不知，代表例子https://github.com/marketplace  会有大约5的误差

        }else if(scrollView is ScrollView){
            val view =  scrollView as ScrollView
             scrollView.scrollY + scrollView.height >= view.getChildAt(0).height
        }else{
            true
        }
    }
}
