package org.example;

import java.util.*;

public class Main {

    static class CircleGeometry {
        private static final int CIRCLE_ARRAY_LENGTH = 3;

        /**
         * Checks whether the center of the source circle lies
         * within the area of the target circle.
         *
         * @param source The circle whose center we need to check, in the form of [x, y, r].
         * @param inTarget The circle within whose area we need to check, in the form of [x, y, r].
         * @return true if the center of the source center lies within the area of the target circle.
         */
        private static boolean isCenterInsideTargetCircle(int[] source, int[] inTarget) {
            if (source.length < CIRCLE_ARRAY_LENGTH || inTarget.length < CIRCLE_ARRAY_LENGTH) {
                throw new IllegalArgumentException(
                        "source and target must have at least " + CIRCLE_ARRAY_LENGTH + " elements"
                );
            }
            if (getRadius(source) < 0 || getRadius(inTarget) < 0) {
                throw new IllegalArgumentException("radius can't be negative");
            }
            int dx = getX(source) - getX(inTarget);
            int dy = getY(source) - getY(inTarget);
            int distanceSquared = dx * dx + dy * dy;
            return getRadius(inTarget) * getRadius(inTarget) >= distanceSquared;
        }

        private static int getX(int[] arr) {
            return arr[0];
        }

        private static int getY(int[] arr) {
            return arr[1];
        }

        private static int getRadius(int[] arr) {
            return arr[2];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 1, 3};
        int[] arr2 = {6, 1, 4};

        test(arr2, arr1, false);
        test(arr1, arr2, true);

        additionalTestCases();

        System.out.println("success.");
    }

    private static void test(int[] source, int[] inTarget, boolean expected) {
        boolean actual = CircleGeometry.isCenterInsideTargetCircle(source, inTarget);
        if (expected != actual) {
            throw new AssertionError(
                    "[ERROR] source: %s, inTarget: %s, expected: %b, actual: %b".formatted(
                            Arrays.toString(source), Arrays.toString(inTarget),
                            expected, actual
                    )
            );
        }
    }

    private static void additionalTestCases() {
        // source center is exactly at the target center
        int[] arr3 = {5, 5, 2};
        int[] arr4 = {5, 5, 3};
        test(arr3, arr4, true);

        // source center is just outside the target's range
        int[] arr5 = {10, 10, 1};
        int[] arr6 = {7, 7, 4};
        test(arr5, arr6, false);

        // source center is just inside the target's range
        int[] arr7 = {8, 8, 1};
        int[] arr8 = {10, 10, 3};
        test(arr7, arr8, true);

        // source and target are identical
        int[] arr9 = {4, 4, 5};
        int[] arr10 = {4, 4, 5};
        test(arr9, arr10, true);

        // source and target are far apart
        int[] arr11 = {0, 0, 1};
        int[] arr12 = {100, 100, 1};
        test(arr11, arr12, false);

        // source center is within target, but source radius is larger
        int[] arr13 = {3, 3, 10};
        int[] arr14 = {3, 3, 5};
        test(arr13, arr14, true);

        // testing with negative coordinates
        int[] arr15 = {-5, -5, 3};
        int[] arr16 = {-6, -6, 2};
        test(arr15, arr16, true);
    }
}