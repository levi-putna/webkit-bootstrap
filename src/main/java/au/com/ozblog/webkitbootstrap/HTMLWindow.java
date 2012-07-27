/**
 * 
 */
package au.com.ozblog.webkitbootstrap;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author leviputna
 * 
 */
public class HTMLWindow extends Shell {

	private Browser browser;

	/**
	 * Create a new instance of an SWT Browser Window
	 * 
	 * <p>
	 * Note: Currently, null can be passed in for the parent. This has the
	 * effect of creating the shell on the currently active display if there is
	 * one. If there is no current display, the shell is created on a "default"
	 * display. <b>Passing in null as the parent is not considered to be good
	 * coding style, and may not be supported in a future release of SWT.</b>
	 * </p>
	 * 
	 * <pre>
	 * display = Display.getDefault();
	 * applicationWindow = new HTMLWindow(display);
	 * applicationWindow.setText(&quot;HTML Test Application&quot;);
	 * applicationWindow.setMinimumSize(700, 550);
	 * applicationWindow.setSize(1024, 750);
	 * applicationWindow.open();
	 * applicationWindow.setFile(&quot;www/html/main.htm&quot;);
	 * </pre>
	 * 
	 * @param display
	 *            the display to create the shell on
	 * 
	 * @see org.eclipse.swt.widgets.Shell;
	 */
	public HTMLWindow(Display display) {
		super(display, SWT.SHELL_TRIM);
		createContents();
	}

	/**
	 * Create a new instance of an SWT Browser Window
	 * 
	 * <p>
	 * See {@link #HTMLWindow(Display)} for an example usage.
	 * 
	 * @param display
	 *            the display to create the shell on
	 * @param style
	 *            the style of control to construct
	 * 
	 * @see org.eclipse.swt.widgets.Shell;
	 */
	public HTMLWindow(Display display, int style) {
		super(display, style);
		createContents();
	}

	private void createContents() {
		setLayout(new FillLayout(SWT.HORIZONTAL));
		setText("HTML Application");
		setSize(450, 300);

		try {
			setBrowser(new Browser(HTMLWindow.this, SWT.NONE | SWT.WEBKIT));

			browser.addTitleListener(new TitleListener() {
				public void changed(TitleEvent event) {
					HTMLWindow.this.setText(event.title);
				}
			});

		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: "
					+ e.getMessage());
			getDisplay().dispose();
			return;
		}
	}

	/**
	 * Marks the window visible, sets the focus and asks the window manager to
	 * make the shell active.
	 * 
	 * <p>
	 * Moves the receiver to the top of the drawing order for the display on
	 * which it was created (so that all other shells on that display, which are
	 * not the receiver's children will be drawn behind it),
	 */
	public void open() {
		super.open();
	}

	/**
	 * Set the url used to define the window's body content. If you wish to use
	 * a local file as the window body use {@link #setFile(String)} instead
	 * 
	 * @param url
	 *            the url Example: "http://www.google.com"
	 */
	public void setURL(String url) {
		if (!browser.isDisposed() && browser != null) {
			browser.setUrl(url);
		} else {
			// throw exception
			System.out.println("ddd");
		}
	}

	/**
	 * Set the file used to define the window's body content. If you wish to use
	 * url as the window body use {@link #setURL(String)} instead
	 * 
	 * @param uri
	 *            the path to a file to use as the body content. Example:
	 *            "http://www.google.com"
	 */
	public void setFile(String uri) {
		if (!browser.isDisposed() && browser != null) {
			File f = new File(uri);
			browser.setUrl(f.toURI().toString());
		} else {
			// throw exception
			System.out.println("ddd");
		}
	}

	/**
	 * get the browser used by this window
	 * 
	 * @return the browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * set the browser used by this window
	 * 
	 * @param browser
	 *            the browser to set
	 */
	private void setBrowser(Browser browser) {
		this.browser = browser;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
