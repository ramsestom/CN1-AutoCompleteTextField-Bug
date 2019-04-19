package org.mycompany.test;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Form;
import com.codename1.ui.list.DefaultListModel;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.table.TableLayout;

public class SecondForm extends Form {

	
	Form next_form = null;
	private String validate_button_text;
	
	public SecondForm() {
		this(com.codename1.ui.util.Resources.getGlobalResources());
	}
    
    public SecondForm(com.codename1.ui.util.Resources resourceObjectInstance) {
    	initGuiBuilderComponents(resourceObjectInstance);
    }
    
    public void setNextForm(Form f) {
    	this.next_form=f;
    }
    
    public void setPreviousForm(Form f) {
    	getToolbar().setBackCommand("", e -> {
    		//unbindAll();
    		f.showBack();	
    	});
    }


    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) 
    {
    	setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
        setTitle("Second Form");
        setName("SecondForm");
        
        TableLayout gui_table_layout = new TableLayout(7, 2);
        com.codename1.ui.Container gui_table_container = new com.codename1.ui.Container(gui_table_layout);
        gui_table_container.setInlineStylesTheme(resourceObjectInstance);
        gui_table_container.setName("Table_Layout");
        
        
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField gui_TF = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
//                if(text.length() == 0) {
//                    return false;
//                }
                String[] l = getSuggestions(text); //searchLocations(text);
                if(l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for(String s : l) {
                    options.addItem(s);
                }
                return true;
            }
        };
        gui_TF.setMinimumElementsShownInPopup(2);
        gui_TF.setHint("Click me. No popup = bug");
        gui_table_container.addComponent(gui_TF);

      
        if (validate_button_text != null) {
	        com.codename1.ui.Button gui_okButton = new com.codename1.ui.Button();
	        gui_okButton.setPreferredSizeStr("inherit inherit");
	        gui_okButton.setText(validate_button_text);
	        gui_okButton.setInlineStylesTheme(resourceObjectInstance);
	        gui_okButton.setName("okButton");
	        gui_okButton.addActionListener(new ActionListener<ActionEvent>() {
				public void actionPerformed(ActionEvent evt) {
					onokButtonActionEvent(evt);
				}
			});
	        addComponent(gui_okButton);
	        addComponent(gui_table_container);
	        
	        ((com.codename1.ui.layouts.LayeredLayout)gui_okButton.getParent().getLayout()).setInsets(gui_okButton, "auto 5.0mm 5.0mm 5.0mm").setReferenceComponents(gui_okButton, "-1 -1 -1 -1").setReferencePositions(gui_okButton, "0.0 0.0 0.0 0.0");
	        ((com.codename1.ui.layouts.LayeredLayout)gui_table_container.getParent().getLayout()).setInsets(gui_table_container, "5.0mm 5.0mm 5.0mm 5.0mm").setReferenceComponents(gui_table_container, "-1 -1 0 -1").setReferencePositions(gui_table_container, "0.0 0.0 1.0 0.0");
	    }
        else {
        	addComponent(gui_table_container);
        	((com.codename1.ui.layouts.LayeredLayout)gui_table_container.getParent().getLayout()).setInsets(gui_table_container, "5.0mm 5.0mm 5.0mm 5.0mm").setReferenceComponents(gui_table_container, "-1 -1 -1 -1").setReferencePositions(gui_table_container, "0.0 0.0 0.0 0.0");
        }  
    }

    
    public void onokButtonActionEvent(com.codename1.ui.events.ActionEvent ev) {
    	if (next_form!=null) {
    		next_form.show();
    	}
    }
    		
	public static String[] getSuggestions(String text) { //Pour test. Retourner des objets brands dans la version finale
		return new String[] {"Ba", "Be", "Bi", "Bo", "Bu", "Ca", "Ci", "De"};
	}
}
