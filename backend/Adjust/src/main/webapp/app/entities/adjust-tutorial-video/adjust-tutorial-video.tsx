import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-tutorial-video.reducer';
import { IAdjustTutorialVideo } from 'app/shared/model/adjust-tutorial-video.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTutorialVideoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustTutorialVideo = (props: IAdjustTutorialVideoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustTutorialVideoList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-tutorial-video-heading">
        <Translate contentKey="adjustApp.adjustTutorialVideo.home.title">Adjust Tutorial Videos</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustTutorialVideo.home.createLabel">Create new Adjust Tutorial Video</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustTutorialVideoList && adjustTutorialVideoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTutorialVideo.content">Content</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustTutorialVideoList.map((adjustTutorialVideo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustTutorialVideo.id}`} color="link" size="sm">
                      {adjustTutorialVideo.id}
                    </Button>
                  </td>
                  <td>
                    {adjustTutorialVideo.content ? (
                      <div>
                        {adjustTutorialVideo.contentContentType ? (
                          <a onClick={openFile(adjustTutorialVideo.contentContentType, adjustTutorialVideo.content)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustTutorialVideo.contentContentType}, {byteSize(adjustTutorialVideo.content)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustTutorialVideo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustTutorialVideo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustTutorialVideo.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustTutorialVideo.home.notFound">No Adjust Tutorial Videos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustTutorialVideo }: IRootState) => ({
  adjustTutorialVideoList: adjustTutorialVideo.entities,
  loading: adjustTutorialVideo.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTutorialVideo);
