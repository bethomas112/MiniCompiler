	.comm bob,8,8
	.comm b,8,8
	.comm bb,8,8
	.comm j,8,8
	.comm k,8,8
	.comm bbb,8,8
	.comm i,8,8
	.section	.rodata
.PRINTSTRING:
	.string "%ld"
.PRINTLNSTRING:
	.string "%ld\n"
.READSTRING:
	.string "%ld"
	.text
.global f
	.type	f, @function
f:
	movq %rdi, r0
	movq %rsi, r1
	movq b(%rip), r5
	movq 0(r5), r6
	movq 24(r6), r7
	movq 24(r7), r8
	movq 24(r8), r9
	movq r9, $rax
	jmp L0
L0:
	addq $24, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	f, .-f
	.text
.global foo
	.type	foo, @function
foo:
	movq %rdi, r0
L2:
	movq $0, r1
	cmpq r0, r1
	movq $0, r2
	movq $1, r3
	cmovleq r3, r2
	cmpq $1, r2
	je L3
	jmp L4
L3:
	movq $1, r4
	movq r4, $rax
	jmp L1
L4:
	movq $1, r5
	movq r0, r6
	subq r5, r6
	movq r6, %rdi
	call foo
	movq %rax, r7
	movq r0, r8
	imulq r7, r8
	movq r8, $rax
	jmp L1
L1:
	addq $0, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	foo, .-foo
	.text
.global g
	.type	g, @function
g:
	movq %rdi, r0
	movq %rsi, r1
	movq $3, r5
	movq r5, $rax
	jmp L6
L6:
	addq $24, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	g, .-g
	.text
.global main
	.type	main, @function
main:
	movq $2, r6
	movq r6, r1
L8:
	movq $1, r7
	movq $0, r8
	movq r7, %rdi
	movq r8, %rsi
	call g
	movq %rax, r9
	cmpq r1, r9
	movq $0, r10
	movq $1, r11
	cmovlq r11, r10
	cmpq $1, r10
	je L9
	jmp L11
L9:
	movq $1, r12
	movq $.PRINTLNSTRING, %rdi
	movq r12, %rsi
	call printf
	jmp L11
L11:
L12:
	movq $1, r13
	movq $0, r14
	movq r13, %rdi
	movq r14, %rsi
	call g
	movq %rax, r15
	cmpq r1, r15
	movq $0, r16
	movq $1, r17
	cmovgq r17, r16
	cmpq $1, r16
	je L13
	jmp L14
L13:
	movq $1, r18
	movq $.PRINTLNSTRING, %rdi
	movq r18, %rsi
	call printf
	jmp L15
L14:
	movq $3, r19
	movq $.PRINTLNSTRING, %rdi
	movq r19, %rsi
	call printf
	jmp L15
L15:
L16:
	movq $1, r20
	cmpq $1, r20
	je L18
	jmp L17
L18:
	movq $7, r21
	movq $.PRINTLNSTRING, %rdi
	movq r21, %rsi
	call printf
	jmp L16
L17:
	movq $1, r23
	movl $8, %edi
	call malloc
	movq %rax, r24
	movq r23, %rdi
	movq r24, %rsi
	call g
	movq %rax, r25
	movl $8, %edi
	call malloc
	movq %rax, r26
	movq r25, %rdi
	movq r26, %rsi
	call f
	movq $1, r27
	movl $8, %edi
	call malloc
	movq %rax, r28
	movq r27, %rdi
	movq r28, %rsi
	call g
	movq %rax, r29
	movl $8, %edi
	call malloc
	movq %rax, r30
	movq r29, %rdi
	movq r30, %rsi
	call f
	movq %rax, r31
	movq 0(r31), r32
	movq $.PRINTLNSTRING, %rdi
	movq r32, %rsi
	call printf
	movq $0, r33
	movq r33, $rax
	jmp L7
L7:
	addq $48, %rsp
	movq %rbp, %rsp
	popq %rbp
	ret
	.size	main, .-main
