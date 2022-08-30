package com.liferay.docs.guestbook.internal.search.spi.model.index.contributor;

public interface GuestbookEntryBatchReindexer {

    public void reindex(long guestbookId, long companyId);

}