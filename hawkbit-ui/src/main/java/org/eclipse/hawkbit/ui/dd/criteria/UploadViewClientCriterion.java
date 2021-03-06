/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.ui.dd.criteria;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * Upload UI View for Accept criteria.
 *
 */
@SpringComponent
@UIScope
public final class UploadViewClientCriterion extends ServerViewClientCriterion {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6271501901430079353L;

    private static final ServerViewComponentClientCriterion[] COMPONENT_CRITERIA = createViewComponentClientCriteria();

    /**
     * Constructor.
     */
    public UploadViewClientCriterion() {
        super(COMPONENT_CRITERIA);
    }

    /**
     * Configures the elements of the composite accept criterion for the Upload
     * View.
     *
     * @return accept criterion elements
     */
    static ServerViewComponentClientCriterion[] createViewComponentClientCriteria() {
        return new ServerViewComponentClientCriterion[0];
    }
}
