from dtree import *
from id3 import *
import sys
from itertools import groupby as g

sys.setrecursionlimit(1500) #needed because python breaks with large vehicle dataset due to ID3 recursion


def read_input():
    # Get the name of the data file and load it into
    if len(sys.argv) < 4:
        # Ask the user for the name of the file
        print "usage: dt.py training_file testing_file output_file",
        sys.exit(0)
    else:
        training_filename = sys.argv[1]
        testing_file = sys.argv[2]
        output_file = sys.argv[3]

    return training_filename,testing_file,output_file

def run_train(fin):
    # The function creates a decision tree based on the input training data

    # Create a list of all the lines in the data file
    lines = fin

    # Remove the attributes from the list of lines and create a list of
    # the attributes.
    lines.reverse()
    attributes = [attr for attr in lines.pop()]
    target_attr = attributes[-1]
    lines.reverse()

    # Create a list of the data in the data file
    data = []
    for line in lines:
        data.append(dict(zip(attributes,
                             [datum.strip() for datum in line])))

    # Copy the data list into the examples list for testing
    examples = data[:]

    # Create the decision tree
    tree = create_decision_tree(data, attributes, target_attr, gain)

    # Classify the records in the examples list
    classification = classify(tree, examples)

    # Print out the classification for each record
    # for item in classification:
    #     print item

    return tree,attributes

def myprint(d):
#Returns every final value in a nested dictionary
    keys = []
    for k, v in d.iteritems():
        if isinstance(v, dict):
            myprint(v)
        else:
            keys.append(v)
    return keys

# returns the most common element in a list, and if there is not one, returns
# the first item in the list
# http://stackoverflow.com/questions/1518522/python-most-common-element-in-a-list
def most_common_oneliner(L):
  return max(g(sorted(L)), key=lambda(x, v):(len(list(v)),-L.index(x)))[0]

if __name__ == "__main__":

    training_filename,testing_file,output_file = read_input()

    with open(training_filename) as f:
        fin = f.readlines()
    fin = [x.strip().split("\t") for x in fin]

    tree,attributes = run_train(fin)

    with open(testing_file) as f:
        testlines = f.readlines()
    test_lines = [x.strip().split("\t") for x in testlines]

    test_attributes = test_lines[0]
    test_data = test_lines[1:]


    f = open(output_file, 'a+') #open output file for writing

    f.write("\t".join(attributes)+"\n")

    for i in test_data:     #iterate through each line of data
        curr_tree = tree
        while type(curr_tree) is dict:
            j = curr_tree.keys()[0]
            next_key = i[test_attributes.index(j)]
            # print("\n")
            # print(j,next_key,curr_tree[j])
            if next_key in curr_tree[j]:
                curr_tree = curr_tree[j][next_key]
            else:
                curr_tree = (most_common_oneliner(myprint(curr_tree[j])))

        f.write("\t".join(i+[curr_tree])+"\n")

    f.close()
