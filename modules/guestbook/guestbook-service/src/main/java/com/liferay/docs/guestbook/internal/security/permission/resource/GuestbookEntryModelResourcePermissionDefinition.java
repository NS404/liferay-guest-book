package com.liferay.docs.guestbook.internal.security.permission.resource;

import com.liferay.docs.guestbook.constants.GuestbookConstants;
import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.GuestbookEntry;
import com.liferay.docs.guestbook.service.GuestbookEntryLocalService;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.sharing.security.permission.resource.SharingModelResourcePermissionConfigurator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import java.util.function.Consumer;

@Component(
        immediate = true,
        service = ModelResourcePermissionDefinition.class
)
public class GuestbookEntryModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<GuestbookEntry> {


    @Override
    public GuestbookEntry getModel(long primaryKey) throws PortalException {

        return _guestbookEntryLocalService.getGuestbookEntry(primaryKey);
    }

    @Override
    public Class<GuestbookEntry> getModelClass() {
        return GuestbookEntry.class;
    }

    @Override
    public PortletResourcePermission getPortletResourcePermission() {
        return _portletResourcePermission;
    }

    @Override
    public long getPrimaryKey(GuestbookEntry guestbookEntry) {
        return guestbookEntry.getEntryId();
    }

    @Override
    public void registerModelResourcePermissionLogics(ModelResourcePermission<GuestbookEntry> modelResourcePermission,
                                                      Consumer<ModelResourcePermissionLogic<GuestbookEntry>>
                                                              modelResourcePermissionLogicConsumer) {
        modelResourcePermissionLogicConsumer.accept(
                new StagedModelPermissionLogic<>(
                        _stagingPermission, GuestbookPortletKeys.GUESTBOOK, GuestbookEntry::getEntryId)
        );

        if (_sharingModelResourcePermissionConfigurator != null) {
            _sharingModelResourcePermissionConfigurator.configure(
                    modelResourcePermission, modelResourcePermissionLogicConsumer);
        }
    }

    @Reference(
        cardinality = ReferenceCardinality.OPTIONAL,
        policyOption = ReferencePolicyOption.GREEDY
    )
    private volatile SharingModelResourcePermissionConfigurator _sharingModelResourcePermissionConfigurator;
    @Reference
    private StagingPermission _stagingPermission;

    @Reference
    private GuestbookEntryLocalService _guestbookEntryLocalService;

    @Reference(
            target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")"
    )
    private PortletResourcePermission _portletResourcePermission;

}
