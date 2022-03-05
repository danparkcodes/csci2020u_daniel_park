"""
StatsCache.py
Author: Daniel Park
References: 
https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
https://www.playingnumbers.com/2019/12/how-to-get-nba-data-using-the-nba_api-python-module-beginner/

This module implement part of the Prototype design pattern
It uses the prototype interface from StatsSheet to cache objects
Objects are created and cached
Data is retrieved once from a call to an NBA stats api
"""

import StatsSheet
import copy
from abc import ABC, abstractmethod
from this import d
import pandas
from nba_api.stats.library.parameters import SeasonAll
from nba_api.stats.endpoints import playergamelog, leaguegamefinder
from nba_api.stats.static import teams
from nba_api.stats.static import players
player_dict = players.get_players()
team_dict = teams.get_teams()

# Hashtable for stats
statsMap = {}

# GetStats: Returns cloned StatsSheet from hashtable
# Takes tuple


def getStats(id):
    # Create tuple from *args (ie, Player, Year)
    # Retrieve StatsSheet and clone it
    statsSheet = statsMap[id]
    cachedStatsSheet = copy.deepcopy(statsSheet)
    return cachedStatsSheet


def getTeamYearStats():
    # Get every season gamelog for every player and store in dictionary cache
    for team in team_dict:
        gamefinder = leaguegamefinder.LeagueGameFinder(
            team_id_nullable=int(team['id']))
        # Converts gamefinder object into a pandas dataframe
        # Gets all games for one team
        team_games_all = gamefinder.get_data_frames()[0]
        # Create StatsSheet object
        teamStats = StatsSheet.TeamYearStats(
            team_games_all, team_games_all['id'])
        # Add StatsSheet Object to hashtable
        statsMap[teamStats['id']] = teamStats


def getPlayerYearStats():
    # Retrieve player year stats from nba_api database and store in dictionary cache
    for player in player_dict:
        gamelog_all = playergamelog.PlayerGameLog(
            player_id=player.id, season=SeasonAll.all)
        # Converts gamelog object into a pandas dataframe
        player_games_all = gamelog_all.get_data_frames()
        # Create StatsSheet object
        teamStats = StatsSheet.PlayerYearStats(
            player_games_all, player_games_all['id'])
        # Add StatsSheet Object to hashtable
        statsMap[player_games_all['id']] = player_games_all


def loadCache():
    # Query nba_api database, create StatsSheets and cache into dictionary
    getTeamYearStats()
    getPlayerYearStats()
