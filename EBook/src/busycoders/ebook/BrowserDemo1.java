package busycoders.ebook;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserDemo1 extends Activity {
	WebView browser;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);
		browser = (WebView) findViewById(R.id.webkit);
		browser.setWebViewClient(new Callback());

		loadTime();
	}

	private void loadTime() {
		String page = "<html><body><a href='clock'>"
				+ DateUtils
						.formatDateTime(this, new Date().getTime(),
								DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_SHOW_TIME)
				+ "</a></body></html>";
		browser.loadData(page, "text/html", "UTF-8");
	}

	class Callback extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			loadTime();
			return (true);
		}
	}
}
