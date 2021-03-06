/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.ui.artifacts.smtype.filter;

import org.eclipse.hawkbit.repository.EntityFactory;
import org.eclipse.hawkbit.repository.SoftwareModuleTypeManagement;
import org.eclipse.hawkbit.ui.SpPermissionChecker;
import org.eclipse.hawkbit.ui.artifacts.event.UploadArtifactUIEvent;
import org.eclipse.hawkbit.ui.artifacts.smtype.CreateSoftwareModuleTypeLayout;
import org.eclipse.hawkbit.ui.artifacts.state.ArtifactUploadState;
import org.eclipse.hawkbit.ui.common.event.FilterHeaderEvent.FilterHeaderEnum;
import org.eclipse.hawkbit.ui.common.event.SoftwareModuleTypeFilterHeaderEvent;
import org.eclipse.hawkbit.ui.common.filterlayout.AbstractFilterHeader;
import org.eclipse.hawkbit.ui.utils.SPUILabelDefinitions;
import org.eclipse.hawkbit.ui.utils.UIComponentIdProvider;
import org.eclipse.hawkbit.ui.utils.UINotification;
import org.eclipse.hawkbit.ui.utils.VaadinMessageSource;
import org.vaadin.spring.events.EventBus.UIEventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.Command;

/**
 * Software module type filter buttons header.
 */
public class SMTypeFilterHeader extends AbstractFilterHeader {

    private static final long serialVersionUID = 1L;

    private final ArtifactUploadState artifactUploadState;

    private final transient EntityFactory entityFactory;

    private final UINotification uiNotification;

    private final transient SoftwareModuleTypeManagement softwareModuleTypeManagement;

    private final SMTypeFilterButtons smTypeFilterButtons;

    SMTypeFilterHeader(final VaadinMessageSource i18n, final SpPermissionChecker permChecker, final UIEventBus eventBus,
            final ArtifactUploadState artifactUploadState, final EntityFactory entityFactory,
            final UINotification uiNotification, final SoftwareModuleTypeManagement softwareModuleTypeManagement,
            final SMTypeFilterButtons smTypeFilterButtons) {
        super(permChecker, eventBus, i18n);
        this.artifactUploadState = artifactUploadState;
        this.entityFactory = entityFactory;
        this.uiNotification = uiNotification;
        this.softwareModuleTypeManagement = softwareModuleTypeManagement;
        this.smTypeFilterButtons = smTypeFilterButtons;
    }

    @Override
    protected String getTitle() {
        return SPUILabelDefinitions.TYPE;
    }

    @Override
    protected boolean dropHitsRequired() {
        return false;
    }

    @Override
    protected void hideFilterButtonLayout() {
        artifactUploadState.setSwTypeFilterClosed(true);
        getEventBus().publish(this, UploadArtifactUIEvent.HIDE_FILTER_BY_TYPE);
    }

    @Override
    protected String getConfigureFilterButtonId() {
        return UIComponentIdProvider.ADD_SOFTWARE_MODULE_TYPE;
    }

    @Override
    protected String getHideButtonId() {
        return UIComponentIdProvider.SM_SHOW_FILTER_BUTTON_ID;
    }

    @Override
    protected boolean isAddTagRequired() {
        return true;
    }

    @Override
    protected Command getAddButtonCommand() {
        return command -> new CreateSoftwareModuleTypeLayout(getI18n(), entityFactory, getEventBus(), getPermChecker(),
                uiNotification, softwareModuleTypeManagement);
    }

    @Override
    protected Command getDeleteButtonCommand() {
        return command -> {
            smTypeFilterButtons.addDeleteColumn();
            getEventBus().publish(this, new SoftwareModuleTypeFilterHeaderEvent(FilterHeaderEnum.SHOW_CANCEL_BUTTON));
        };
    }

    @Override
    protected Command getUpdateButtonCommand() {
        return command -> {
            smTypeFilterButtons.addUpdateColumn();
            getEventBus().publish(this, new SoftwareModuleTypeFilterHeaderEvent(FilterHeaderEnum.SHOW_CANCEL_BUTTON));
        };
    }

    @Override
    protected void cancelUpdateOrDeleteTag(final ClickEvent event) {
        super.cancelUpdateOrDeleteTag(event);
        smTypeFilterButtons.removeUpdateAndDeleteColumn();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEvent(final SoftwareModuleTypeFilterHeaderEvent event) {
        processFilterHeaderEvent(event);
    }

    @Override
    protected String getMenuBarId() {
        return UIComponentIdProvider.SOFT_MODULE_TYPE_MENU_BAR_ID;
    }

}
