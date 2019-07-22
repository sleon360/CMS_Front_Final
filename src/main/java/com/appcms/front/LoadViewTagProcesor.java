package com.appcms.front;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class LoadViewTagProcesor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "load";
    private static final int PRECEDENCE = 1;


    public LoadViewTagProcesor(final String dialectPrefix) {
        super(
            TemplateMode.HTML, // This processor will apply only to HTML mode
            dialectPrefix,     // Prefix to be applied to name for matching
            null,              // No tag name: match any tag name
            true,             // No prefix to be applied to tag name
            ATTR_NAME,         // Name of the attribute that will be matched
            true,              // Apply dialect prefix to attribute name
            PRECEDENCE,        // Precedence (inside dialect's precedence)
            true);             // Remove the matched attribute afterwards
    }


 protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,final AttributeName attributeName, final String attributeValue,final IElementTagStructureHandler structureHandler) {
    	
    		//ViewApp vi=new ViewApp();
    		//vi.addView(attributeValue);
    		//vi.addView(rq,"admin");
    		//final IModelFactory modelFactory = context.getModelFactory();
            //final IModel model = modelFactory.createModel();

            //model.add(modelFactory.createText(vi.render()));
            // structureHandler.replaceWith(model, true);
    		//structureHandler.setBody(vi.render(), true);
    		//structureHandler.setBody(HtmlEscape.escapeHtml5("test"), true);
    }


}