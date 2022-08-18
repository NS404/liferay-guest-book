package com.liferay.docs.guestbook.internal.security.permission.resource;

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.sharing.security.permission.resource.SharingModelResourcePermissionConfigurator;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import java.util.function.Consumer;

public class GuestbookModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<Guestbook> {
    @Override
    public Guestbook getModel(long primaryKey) throws PortalException {
        return _guestbookLocalService.getGuestbook(primaryKey);
    }

    @Override
    public Class<Guestbook> getModelClass() {
        return Guestbook.class;
    }

    @Override
    public PortletResourcePermission getPortletResourcePermission() {
        return _portletResourcePermission;
    }

    @Override
    public long getPrimaryKey(Guestbook guestbook) {
        return guestbook.getGuestbookId();
    }

    @Override
    public void registerModelResourcePermissionLogics(ModelResourcePermission<Guestbook> modelResourcePermission,
                                                      Consumer<ModelResourcePermissionLogic<Guestbook>> modelResourcePermissionLogicConsumer) {

            modelResourcePermissionLogicConsumer.accept(
                    new StagedModelPermissionLogic<>(
                            _stagingPermission, GuestbookPortletKeys.GUESTBOOK_ADMIN,
                            Guestbook::getGuestbookId
                    )
            );

            if (_sharingModelResourcePermissionConfigurator != null) {
                _sharingModelResourcePermissionConfigurator.configure(
                        modelResourcePermission, modelResourcePermissionLogicConsumer);
            }
    }

    @Reference
    private GuestbookLocalService _guestbookLocalService;

    @Reference(
            target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission _portletResourcePermission;

        @Reference(
                cardinality = ReferenceCardinality.OPTIONAL,
                policyOption = ReferencePolicyOption.GREEDY
        )
        private volatile SharingModelResourcePermissionConfigurator _sharingModelResourcePermissionConfigurator;

        @Reference
        private StagingPermission _stagingPermission;
}

