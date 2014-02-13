package busycoders.ebook;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class BrowserDemo1 extends Activity{
	WebView browser;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main);
		browser=(WebView)findViewById(R.id.webkit);
		browser.loadUrl("http://naver.com");
	}
}
