"""
# StatsSheet.py
Author: Daniel Park
References: 
https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
https://www.playingnumbers.com/2019/12/how-to-get-nba-data-using-the-nba_api-python-module-beginner/

This scrip/module implement an abstract class StatsSheet
Stats are for the NBA
Each implementation of StatsSheet represent a differnt set of NBA stats
"""

# Program showing prototype creator design pattern

from abc import ABC, abstractmethod


class StatsSheet(ABC):
    """
    Abstract class used to represent nba stats

    Attributes
    -----------
    type: string
        the type of statsheet (ie top 10 scorers, a team's last 10 games..)
    stats: pandas dataframe
        the statsheet stored in tabular form
    id: integer
        the unique identifier of the statsheet

    Methods
    ---------
    printStats
        Prints the stats sheet in the console
    """
    @abstractmethod
    def __init__(self, stats, id):
        self.__type = type
        self.__stats = stats
        self.__id = id

    @abstractmethod
    def printStats(self):
        pass


class TeamYearStats(StatsSheet):
    """
    Class to represent game stats for a team in a year
    """

    def __init__(self, stats, id):
        self.__type = 'TeamYearStats'
        self.__stats = stats
        self.__id = id

    # Dataframe variable (Team + Year)
    def printStats(self):
        print('Team stats: \n' + self.__statsstats)


class PlayerYearStats(StatsSheet):
    """
    Class to represent player stats for a player in a year
    """

    def __init__(self, stats, *args):
        self.__type = 'PlayerYearStats'
        self.__stats = stats

    def printStats(self):
        print('Player stats: \n' + self.__statsstats)
