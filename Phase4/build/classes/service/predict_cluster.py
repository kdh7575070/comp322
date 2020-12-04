import sys

from joblib import dump, load
from kmodes.kmodes import KModes
import numpy as np


def main(s1, s2, s3):
    kmode = load('kmode.joblib')
    new_account = np.array([[int(s1), int(s2), int(s3)]])
    print(kmode.predict(new_account)[0])


if __name__ == '__main__':
    main(sys.argv[1], sys.argv[2], sys.argv[3])

