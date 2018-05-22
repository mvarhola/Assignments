# Assignment 1 for DATA SCIENCE
# Student: Markiyan Varhola
# Student ID: 9016820171
# Student Email: mvarhola@hawk.iit.edu

import sys
import itertools
import collections

min_support = float(sys.argv[1])
in_filename = str(sys.argv[2])
out_filename = str(sys.argv[3])

#count the occurence of each item and place into dictionary
#the dictionary contains item -> support count
def count_items(transactions):
    apriori_dict = {}
    for i in transactions:
        for item in i:
            item = (item,)
            if item in apriori_dict.keys():
                apriori_dict[item] += 1
            else:
                apriori_dict[item] = 1
    return apriori_dict

#return dictionary with items containing support > minimum support
def check_support(candidates, min_support, total_transactions):
    apriori_dict = {}
    for k,v in candidates.items():
        if float(v)/float(total_transactions) >= float(min_support/100):
            apriori_dict[k] = v
    return apriori_dict

#generate candidate sets from base dictionary
def gen_candidates(key_list):
    apriori_dict = {}
    for i in key_list:
        if type(i) is int:
            i = frozenset([i])
        else:
            i = frozenset(i)
        for j in key_list:
            if type(j) is int:
                j = frozenset([j])
            else:
                j = frozenset(j)
            if i != j:
                key = (i|j)
                if tuple(key) not in apriori_dict.keys():
                    apriori_dict[tuple(key)] = 0
    return apriori_dict

#update support count for candidate sets
def update_support_count(candidates, transactions):
    for i in candidates.keys():
        for t in transactions:
            if set(i).issubset(t):
                candidates[i] += 1

#generate a dictionary of rules
#dictionary contains item set -> associative set
def gen_rules(f_list):
    apriori_dict = {}
    for i in itertools.permutations(f_list, 2):
        if not any(x in i[0] for x in i[1]):
            if i[0] not in apriori_dict:
                apriori_dict[i[0]] = []
                apriori_dict[i[0]].append(i[1])
            else:
                apriori_dict[i[0]].append(i[1])
    return apriori_dict

#read input file
f = open(in_filename,"r+");

transaction_list = []
candidate_dict = {}

#read each line of file and place each transaction into a list
for line in f:
    x = [int(i) for i in line.split()]
    transaction_list.append(x)

f.close()


apriori_dict = {}
apriori_base_dict = {}
C1 = count_items(transaction_list)
max_iterations = len(C1.keys())
candidates = C1;
f = check_support(candidates, min_support, len(transaction_list))
apriori_dict.update(f)

#generate candidate sets of a maximum size same as number of distinct items
for i in range(max_iterations):
    candidates = gen_candidates(f.keys())
    update_support_count(candidates,transaction_list)
    f = check_support(candidates, min_support, len(transaction_list))
    apriori_dict.update(f)

apriori_dict.update(apriori_base_dict)


#Create a unique dictionary of keys
testdict = {}
for i,k in apriori_dict.iteritems():
    if tuple(sorted(i)) not in testdict.keys():
        testdict[tuple(sorted(i))] = k
apriori_dict = collections.OrderedDict(sorted(testdict.items()))

#Create Rule Dictionary
rule_dict = {}
for k, v in gen_rules(apriori_dict.keys()).iteritems():
    # print(k,v)
    for i in v:
        if k not in rule_dict:
            rule_dict[k]=[(i)]
        else:
            rule_dict[k].append(i)

#Create output file
wf = open(out_filename, 'w')

#for each rule, print item set, associative set, and calculate support and confidence
for k,v in rule_dict.iteritems():
    for i in v:
        if k in apriori_dict.keys():

            item_set = list(k)
            associative_set = list(i)
            full_set = sorted(item_set+associative_set)

            if tuple(full_set) in apriori_dict:
                support = (float(apriori_dict[tuple(full_set)])/len(transaction_list))*100

                if tuple(item_set) in apriori_dict:
                    item_set_support = float(apriori_dict[tuple(item_set)])/float(len(transaction_list))*100.00
                    confidence = support / item_set_support * 100

                    item_set_text = ','.join(map(str, item_set))
                    associative_set_text = ','.join(map(str, associative_set))

                    put_string = "{}{}{}\t{}{}{}\t{:.2f}\t{:.2f}\n".format("{",item_set_text,"}","{",associative_set_text,"}",support,confidence)
                    wf.write(put_string)

wf.close()
