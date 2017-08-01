/*
 * LinuxSystem.cpp
 *
 *  Created on: Jul 30, 2017
 *      Author: kagy
 */

#include "LinuxSystem.h"

#if defined(__linux__)



/**
 * The init function that should transform the current process into a daemon.
 */
void LinuxSystem::init(const std::string workDir)
{
	//Process Identifier
	pid_t pid;
	int r;

	System::pSystem=this;

	//first fork in order to create an orphan process
	pid=fork();

	//exiting on fork error
	if (pid<0)
		exit(-1);

	//exiting from the parent process, making the child orphan
	if (pid>0)
		exit(0);

	//making a new session, where the orphan child is the session leader
	pid=setsid();
	if (pid<0) //can not create a new session
		exit(-2);

	//setting up signal handlers
	signal(SIGCHLD, SIG_IGN);
	signal(SIGHUP, SIG_IGN);

	//forking to create the orphan grand child
	pid=fork();
	if (pid<0) //in case of an error
		exit(-1);

	//exiting the parent process of the grand child process
	if (pid>0)
		exit(0);

	//setting new file permissions
	umask(S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
	//changing the current directory to the working directory
	r=chdir(workDir.c_str());
	if (r<0)
		exit(-1);

	//closing all open file descriptors
	for (int f=sysconf(_SC_OPEN_MAX); f>=0; f--)
		close(f);
}

#endif //defined(__linux__)
