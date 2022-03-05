"""
nbaStats.py
Author: Daniel Park
References: 
https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
https://www.playingnumbers.com/2019/12/how-to-get-nba-data-using-the-nba_api-python-module-beginner/

This module does gets clones of StatsSheet object from StatsSheet dictionary
Clones are used as part of Prototype pattern to improve performance
Lengthy stats files are retrieved from NBA api, and are a costly operation
So these stats are instead cached to reduce database calls
"""

import StatsCache


def main():
    #
    StatsCache.loadCache()
    detroitPistons = StatsCache.getStats('Detroit Pistons')
    print(detroitPistons)
    lebronJames = StatsCache.getStats('Lebron James')
    print(lebronJames)
    torontoRaptors = StatsCache.getStats('Toronto Raptors')
    print(torontoRaptors)


if __name__ == "__main__":
    main()
