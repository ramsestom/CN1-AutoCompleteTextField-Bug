package org.mycompany.test;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

public class FirstForm extends Form {

	public FirstForm() {
		
		setTitle("First form");
		setLayout(new LayeredLayout());
		
		Container itemsContainer = new Container(BoxLayout.y());    
		itemsContainer.setScrollableY(true);
		itemsContainer.add(FlowLayout.encloseCenterMiddle(new InfiniteProgress()));
		itemsContainer.removeAll();
		
    	for (int i=0; i<5; i++) 
    	{
    		Button delete = new Button();
    		delete.setUIID("SwipeableContainerButton");
    		FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE, 8);

    		//ordering options
    		Button up = new Button();
    		up.setUIID("SwipeableVehicleUpButton");
    		FontImage.setMaterialIcon(up, FontImage.MATERIAL_ARROW_UPWARD, 4);

    		Button down = new Button();
    		down.setUIID("SwipeableVehicleDownButton");
    		FontImage.setMaterialIcon(down, FontImage.MATERIAL_ARROW_DOWNWARD, 4);
    		
    		//inactivate up and down button depending on the index
    		if (i==0) {
    			up.setEnabled(false);
    		} else if (i==4) {
    			down.setEnabled(false);
    		}

    		Container options = BoxLayout.encloseY(up,down);

    		
    		Button butt = new Button("button"+i);
    		butt.addActionListener(e-> {
    			SecondForm secform = new SecondForm();
    			secform.setPreviousForm(this);
    			secform.show();
    		});
    		
    		SwipeableContainer sc = new SwipeableContainer(
    				options, 
    				GridLayout.encloseIn(1, delete), 
    				butt);
    		
    		itemsContainer.addComponent(sc);
    	}
    	    	
    	FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
    	fab.addActionListener(e -> {
    		SecondForm secform = new SecondForm();
			secform.setPreviousForm(this);
			secform.show();
    		
    	});
    	this.add(CENTER, fab.bindFabToContainer(itemsContainer));
    		

		addShowListener(e -> {
			ToastBar.showMessage("Click a button before this message disapear to get a chance to see the bug", FontImage.MATERIAL_COMPARE_ARROWS, 2000);        
		});
	}
}
