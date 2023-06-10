package pt.isec.pa.tinypac.model.data.top5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Top5Players implements Serializable {
    private final List<ScoreEntry> entries;

    public Top5Players() {
        entries = new ArrayList<>();
    }

    public void addPlayer(String name, int score) {
        for (ScoreEntry entry : entries) {
            if (entry.getName().equals(name)) {
                if (score > entry.getScore()) {
                    entry.setScore(score);
                    sortEntries();
                    return;
                } else {
                    return; // Player already exists with a higher or equal score, no need to add it again
                }
            }
        }

        // Player doesn't exist, add it as a new entry
        ScoreEntry newEntry = new ScoreEntry(name, score);
        entries.add(newEntry);
        sortEntries();

        if (entries.size() > 5) {
            entries.remove(entries.size() - 1);
        }
    }

    public List<String> getTop5() {
        List<String> result = new ArrayList<>();
        for (ScoreEntry entry : entries) {
            result.add(entry.toString());
        }
        return result;
    }

    private void sortEntries() {
        entries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    }
}
