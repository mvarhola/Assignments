# dbscan.py
# Markiyan Varhola
# 9016820171

import sys
import progressbar
import numpy as np
import math
import time

#read program arguments
filename = str(sys.argv[1])
n_clusters = int(sys.argv[2])
eps_number = int(sys.argv[3])
min_pts_num = int(sys.argv[3])

#Process the dataset into sets containing object IDs and just the points
def loadDataSet(fileName, splitChar='\t'):
    dataSet = []
    points = []
    with open(fileName) as fr:
        for line in fr.readlines():
            curline = line.strip().split(splitChar)
            fullline = list(curline)
            fltline = list(map(float, curline[1:]))
            dataSet.append(fullline)
            points.append(fltline)
    return dataSet,points

#compute distance between two points
def dist(a, b):
    return math.sqrt(np.power(a - b, 2).sum())

#check if the neighbor is within the epsilon value
def eps_neighbor(a, b, eps):
    return dist(a, b) < eps

#query the region to discover neighbors
def region_query(data, pointId, eps):
    nPoints = data.shape[1]
    seeds = []
    for i in range(nPoints):
        if eps_neighbor(data[:, pointId], data[:, i], eps):
            seeds.append(i)
    return seeds

#expand the cluster by repeatedly checking the region for neighbors
def expand_cluster(data, clusterResult, pointId, clusterId, eps, minPts):
    seeds = region_query(data, pointId, eps)
    if len(seeds) < minPts:
        clusterResult[pointId] = 0
        return False
    else:
        clusterResult[pointId] = clusterId
        for seedId in seeds:
            clusterResult[seedId] = clusterId

        while len(seeds) > 0:
            currentPoint = seeds[0]
            queryResults = region_query(data, currentPoint, eps)
            if len(queryResults) >= minPts:
                for i in range(len(queryResults)):
                    resultPoint = queryResults[i]
                    if clusterResult[resultPoint] == False:
                        seeds.append(resultPoint)
                        clusterResult[resultPoint] = clusterId
                    elif clusterResult[resultPoint] == 0:
                        clusterResult[resultPoint] = clusterId
            seeds = seeds[1:]
        return True

#generate clusters
def dbscan(data, eps, minPts):
    clusterId = 1
    nPoints = data.shape[1]
    clusterResult = [False] * nPoints

    print("Hold on! This might take a while...")
    bar = progressbar.ProgressBar(maxval=nPoints, \
    widgets=[progressbar.Bar('=', '[', ']'), ' ', progressbar.Percentage()])
    bar.start()

    for pointId in range(nPoints):
        bar.update(pointId)
        point = data[:, pointId]
        if clusterResult[pointId] == False:
            if expand_cluster(data, clusterResult, pointId, clusterId, eps, minPts):
                clusterId = clusterId + 1
    bar.finish()

    return clusterResult, clusterId - 1

#remove the shortest key value pair inside a dictionary
def remove_smallest_dict_entry(d):
    subdict = d
    # print("Subdict before: "+str(subdict.keys()))
    keys = []
    vals = []
    for k,v in d.iteritems():
        keys.append(k)
        vals.append(len(v))

    del subdict[keys[vals.index(min(vals))]]
    # print("Subdict after: "+str(subdict.keys()))
    return subdict

#main program, 
def main():
    dataSet,points = loadDataSet(filename, splitChar='\t')
    points = np.mat(points).transpose()
    clusters, clusterNum = dbscan(points, eps_number, min_pts_num)
    # print("cluster Numbers = ", clusterNum)
    clusterDict = {}

    #generate dictionary of (clusterId => datapoints)
    for i in range(len(clusters)):
        clusterDict.setdefault(clusters[i],[]).append(dataSet[i][0])

    #remove smallest
    while (clusterNum > n_clusters):
        remove_smallest_dict_entry(clusterDict)
        clusterNum = clusterNum - 1

    for i in range(clusterNum):
        f = open('test/'+filename.split("/")[1].split(".")[0]+'_cluster_'+str(i)+".txt", 'w')
        for j in clusterDict[clusterDict.keys()[i]]:
            f.write(str(j)+'\n')  # python will convert \n to os.linesep
        f.close()  # you can omit in most cases as the destructor will call it

if __name__ == '__main__':
    #time the program runtime
    start = time.clock()
    main()
    end = time.clock()
    print('Finished in %s seconds' % str(end - start))
