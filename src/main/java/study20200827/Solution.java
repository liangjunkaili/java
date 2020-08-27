package study20200827;

import java.util.*;

public class Solution {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> itinerary = new LinkedList<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","B"));
        tickets.add(Arrays.asList("B","C"));
        tickets.add(Arrays.asList("B","D"));
        tickets.add(Arrays.asList("B","E"));
        tickets.add(Arrays.asList("C","JFK"));
        tickets.add(Arrays.asList("D","JFK"));
        tickets.add(Arrays.asList("E","JFK"));
        System.out.println(solution.findItinerary(tickets));
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}
