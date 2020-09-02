package com.hyunsiks.spring5.spring;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DuplicateMemberException extends RuntimeException {

	public DuplicateMemberException(String message) {
		super(message);
	}
}

class ForkJoin {
	static final ForkJoinPool pool = new ForkJoinPool();  // 쓰레드 풀을 생성

	public static void main(String[] args) {
		long from = 1L, to = 100_000_000L;

		SumTask task = new SumTask(from, to);

		long start = System.currentTimeMillis();  // 시작시간 초기화
		long result = pool.invoke(task);

		System.out.println("Elapsed time(4 core) : " + (System.currentTimeMillis() - start));
		System.out.printf("sum of %d - %d = %d\n", from, to, result);
		System.out.println();

		result = 0L;
		start = System.currentTimeMillis();
		for (long i = from; i <= to; i++) {
			result += i;
		}

		System.out.println("Elapsed time(1 core) : " + (System.currentTimeMillis() - start));
		System.out.printf("sum of %d - %d = %d\n", from, to, result);
	}
}

class SumTask extends RecursiveTask<Long> {
	long from, to;

	public SumTask(long from, long to) {
		this.from = from;
		this.to = to;
	}

	@Override
	protected Long compute() {

		// from <= i <= to
		long size = to - from + 1;

		// 더할 숫자가 5개 이하면 숫자의 합을 반환
		if (size <= 5)
			return sum();

		long half = (from + to) / 2;

		// 범위를 반으로 나눠서 두 개의 작업을 생성
		SumTask leftSum = new SumTask(from, half);
		SumTask rightSum = new SumTask(half + 1, to);

		leftSum.fork();

		return rightSum.compute() + leftSum.join();
	}

	long sum() {
		long tmp = 0L;

		for (long i = from; i <= to; i++) {
			tmp += i;
		}

		return tmp;
	}
}