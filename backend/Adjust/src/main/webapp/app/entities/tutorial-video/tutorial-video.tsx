import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tutorial-video.reducer';
import { ITutorialVideo } from 'app/shared/model/tutorial-video.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITutorialVideoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TutorialVideo = (props: ITutorialVideoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { tutorialVideoList, match, loading } = props;
  return (
    <div>
      <h2 id="tutorial-video-heading">
        <Translate contentKey="adjustApp.tutorialVideo.home.title">Tutorial Videos</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.tutorialVideo.home.createLabel">Create new Tutorial Video</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {tutorialVideoList && tutorialVideoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorialVideo.adjustTutorialVideoId">Adjust Tutorial Video Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.tutorialVideo.content">Content</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tutorialVideoList.map((tutorialVideo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tutorialVideo.id}`} color="link" size="sm">
                      {tutorialVideo.id}
                    </Button>
                  </td>
                  <td>{tutorialVideo.adjustTutorialVideoId}</td>
                  <td>
                    {tutorialVideo.content ? (
                      <div>
                        {tutorialVideo.contentContentType ? (
                          <a onClick={openFile(tutorialVideo.contentContentType, tutorialVideo.content)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {tutorialVideo.contentContentType}, {byteSize(tutorialVideo.content)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tutorialVideo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tutorialVideo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tutorialVideo.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.tutorialVideo.home.notFound">No Tutorial Videos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tutorialVideo }: IRootState) => ({
  tutorialVideoList: tutorialVideo.entities,
  loading: tutorialVideo.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TutorialVideo);
