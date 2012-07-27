/**
 * 
 */
package au.com.ozblog.webkitbootstrap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * @author leviputna
 * 
 */
public class ApplicationWindow {

	private static HTMLWindow applicationWindow;
	private static Display display;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Display.setAppName("Webkit Bootstrap Example");
		display = Display.getDefault();
		applicationWindow = new HTMLWindow(display);

		applicationMenu();
		applicationWindow.setText("Webkit Bootstrap Example");
		applicationWindow.setMinimumSize(700, 550);
		applicationWindow.setSize(1024, 750);
		applicationWindow.open();
		applicationWindow.setFile("www/html/main.htm");

		while (!applicationWindow.isDisposed()) {

			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private static void applicationMenu() {

		Menu applicationMenuBar = display.getMenuBar();
		if (applicationMenuBar == null) {
			applicationMenuBar = new Menu(applicationWindow, SWT.BAR);
			applicationWindow.setMenuBar(applicationMenuBar);
		}
		MenuItem file = new MenuItem(applicationMenuBar, SWT.CASCADE);
		file.setText("File");
		
		MenuItem help = new MenuItem(applicationMenuBar, SWT.CASCADE);
		help.setText("Help");

		Menu fileDropdown = new Menu(applicationMenuBar);
		file.setMenu(fileDropdown);
		
		Menu helpDropdown = new Menu(applicationMenuBar);
		help.setMenu(helpDropdown);

		MenuItem exit = new MenuItem(fileDropdown, SWT.PUSH);
		exit.setText("Exit");
		exit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
			};
		});

		MenuItem about = new MenuItem(helpDropdown, SWT.PUSH);
		about.setText("About");
		about.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("About");
				HTMLWindow aboutWindow = new HTMLWindow(display, SWT.CLOSE
						| SWT.TITLE | SWT.MIN | SWT.APPLICATION_MODAL);

				aboutWindow.setText("About WebKit Bootstrap");
				aboutWindow.setMinimumSize(400, 600);
				aboutWindow.open();
				aboutWindow.setFile("www/html/about.htm");
			};
		});
	}
}
