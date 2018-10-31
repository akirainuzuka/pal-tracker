package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository
{
    protected Map<Long,TimeEntry> timeEntryMap;
    private long currentId;

    public InMemoryTimeEntryRepository()
    {
        timeEntryMap = new HashMap<Long,TimeEntry>();
        currentId = 0;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry output = new TimeEntry(++currentId,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeEntryMap.put(output.getId(),output);
        return output;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        ArrayList<TimeEntry> timeEntryArrayList = new ArrayList<>();
        timeEntryArrayList.addAll(timeEntryMap.values());
        return timeEntryArrayList;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {

        timeEntryMap.put(eq,
                new TimeEntry(eq,any.getProjectId(),any.getUserId(),any.getDate(),any.getHours()));

        return timeEntryMap.get(eq);
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }
}